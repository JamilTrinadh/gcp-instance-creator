package com.example.gcp_instance_creator.service;

import org.springframework.stereotype.Service;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.compute.v1.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class GcpInstanceCreator {

    private static final String PROJECT_ID = "green-orb-448400-i2"; // 🔁 Replace with your real project ID
    private static final String MACHINE_TYPE = "n1-standard-1";
    private static final String SOURCE_IMAGE = "projects/debian-cloud/global/images/family/debian-11";
    private static final String NETWORK_INTERFACE = "global/networks/default";
    private static final String CREDENTIALS_PATH = "/Users/jamiltrinadh/downloads/nawat/green-orb-448400-i2-5bf176574b8f.json"; // 🔁 Replace with your actual path

    public String createInstance(String instanceName, String zone) {
        try {
            // Load credentials from the service account JSON
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(CREDENTIALS_PATH));

            // Create an InstancesClient with the provided credentials
            InstancesClient instancesClient = InstancesClient.create(
                    InstancesSettings.newBuilder()
                            .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                            .build()
            );

            // Build the VM instance
            Instance instance = Instance.newBuilder()
                    .setName(instanceName)
                    .setMachineType(String.format("zones/%s/machineTypes/%s", zone, MACHINE_TYPE))
                    .addDisks(AttachedDisk.newBuilder()
                            .setBoot(true)
                            .setAutoDelete(true)
                            .setInitializeParams(AttachedDiskInitializeParams.newBuilder()
                                    .setSourceImage(SOURCE_IMAGE)
                                    .setDiskSizeGb(10) // Set the disk size as needed
                                    .build())
                            .build())
                    .addNetworkInterfaces(NetworkInterface.newBuilder()
                            .setName(NETWORK_INTERFACE) // Ensure that this network exists
                            .build())
                    .build();

            // Insert the VM asynchronously
            Operation operation = instancesClient.insertAsync(PROJECT_ID, zone, instance).get();

            return "VM creation started: " + operation.getName();
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return "Error creating instance: " + e.getMessage();
        }
    }

}
