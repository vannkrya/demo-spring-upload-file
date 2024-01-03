package com.example.demouploadfile.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileResponse<T> {
    private String message;
    private Integer status;
    private T payload;
}
