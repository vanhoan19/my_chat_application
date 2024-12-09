package com.hoanvan.messenger_v1.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConverterUtils {
    @NotNull
    public static byte[] readBytesFromInputStream(InputStream inputStream, int length) {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[length];
            int nRead;
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            return buffer.toByteArray();
        } catch (Exception e) {
            return new byte[0];
        }
    }
    public static LocalDateTime convertToLocalDateTimeFromObject(Object object) {
        LocalDateTime lastModifiedDate = null;
        if (object != null && object instanceof Timestamp) {
            Timestamp timestamp = (Timestamp) object;
            lastModifiedDate = timestamp.toLocalDateTime(); // Chuyển đổi trực tiếp
        }
        return lastModifiedDate;
    }
}