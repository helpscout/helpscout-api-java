package net.helpscout.api;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Page<T> {
    
	Integer page;
	Integer pages;
	Integer count;
	List<T> items;
}