package test;

import java.io.File;
import java.io.IOException;

import writer.CategoryWriter;

public class TestCategoryWriter {
    public static void main(String[] args) throws IOException {
        CategoryWriter writer = new CategoryWriter(new File("test" + File.separator + "testdata" + File.separator));

        writer.createCategory("test");
    } 
}
