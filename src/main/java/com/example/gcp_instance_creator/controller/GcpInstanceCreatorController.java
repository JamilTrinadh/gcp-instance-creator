package com.example.gcp_instance_creator.controller;

import com.example.gcp_instance_creator.service.GcpInstanceCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gcp")
public class GcpInstanceCreatorController {

    @Autowired
    GcpInstanceCreator gcpInstanceCreator ;


    // API to create a GCP instance
    @PostMapping("/create-instance")
    public String createInstance(@RequestParam String instanceName, @RequestParam String zone) {
        // Call the GcpInstanceCreator service to create the instance
        return gcpInstanceCreator.createInstance(instanceName, zone);
    }

    @PostMapping("/create-image")
    public String createImageFromDisk(
            @RequestParam String imageName,
            @RequestParam String diskZone,
            @RequestParam String diskName) {
        return gcpInstanceCreator.createImageFromDisk(imageName, diskZone, diskName);
    }

    @PostMapping("/create-snapshot")
    public String createSnapshot(
            @RequestParam String diskName,
            @RequestParam String zone,
            @RequestParam String snapshotName,
            @RequestParam(defaultValue = "Snapshot created via API") String description
    ) {
        return gcpInstanceCreator.createSnapshot(diskName, zone, snapshotName, description);
    }


}
