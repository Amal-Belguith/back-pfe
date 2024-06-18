package com.care.careplan.security;

import org.springframework.stereotype.Component;

@Component
public class VigenereCipher {

    public String encrypt(String text, String key) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            result.append((char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A'));
            j = ++j % key.length();
        }
        return result.toString();
    }

    public String decrypt(String text, String key) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            result.append((char) ((c - key.charAt(j) + 26) % 26 + 'A'));
            j = ++j % key.length();
        }
        return result.toString();
    }
}


