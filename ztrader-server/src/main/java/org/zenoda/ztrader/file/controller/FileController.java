package org.zenoda.ztrader.file.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zenoda.ztrader.file.service.FileService;

@RestController
@RequestMapping("/files")
public class FileController {
    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Object upload(@RequestPart(value = "file", required = false)
                         MultipartFile file,
                         @RequestPart(value = "files", required = false)
                         MultipartFile[] files,
                         @RequestParam(value = "path")
                         String path) {
        if (file != null) {
            return fileService.uploadSingleFile(file, path);
        }
        if (files != null) {
            return fileService.uploadMultiFiles(files, path);
        }
        return null;
    }

    @GetMapping(value = "/{*file}")
    public ResponseEntity<Resource> getFile(@PathVariable("file") String file, @RequestParam(value = "disposition", defaultValue = "inline") String disposition) {
        if (file.startsWith("/")) {
            file = file.substring(1);
        }
        return fileService.getFile(file, disposition);
    }
}
