package com.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RO {
    private Long currentPage;
    private Long pageSize;
    private Long total;

    private Object result;
}
