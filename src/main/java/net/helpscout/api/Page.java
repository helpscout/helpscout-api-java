package net.helpscout.api;

import java.util.List;

import lombok.Data;

@Data
public class Page<T> {
    
    private Integer page;
    private Integer pages;
    private Integer count;
    private List<T> items;
}