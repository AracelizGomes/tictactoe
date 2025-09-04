package com.tictactoe.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

// Controller to serve static content (HTML, CSS, JS)
@RestController
public class StaticContentController {
    
    @GetMapping("/")
    public ResponseEntity<String> serveIndex() throws IOException {
        return serveStaticFile("static/index.html", MediaType.TEXT_HTML);
    }
    
    @GetMapping("/style.css")
    public ResponseEntity<String> serveCss() throws IOException {
        return serveStaticFile("static/style.css", MediaType.valueOf("text/css"));
    }
    
    @GetMapping("/app.js")
    public ResponseEntity<String> serveJs() throws IOException {
        return serveStaticFile("static/app.js", MediaType.valueOf("application/javascript"));
    }
    
    private ResponseEntity<String> serveStaticFile(String path, MediaType mediaType) throws IOException {
        Resource resource = new ClassPathResource(path);
        
        try (InputStream inputStream = resource.getInputStream()) {
            String content = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(content);
        }
    }
}