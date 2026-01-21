package com.example.demo.infrastructure.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.pff.PSTGlobalObjectId.bytesToHex;

public class fileHashService {
    private static final String HASH_ALGORITHM ="SHA-256";
    private static final int BUFFER_SIZE=8192;
    public String calculateHash(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        return calculateHash(file.getBytes());
    }
    public String calculateHash(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] HashBytes = digest.digest(bytes);
        return bytesToHex(HashBytes);
    }
}
