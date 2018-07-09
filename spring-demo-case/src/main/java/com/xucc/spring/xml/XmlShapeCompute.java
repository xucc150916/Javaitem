package com.xucc.spring.xml;

import com.xucc.spring.common.Shape;

public class XmlShapeCompute {
    private Shape circular;
    private Shape rectangle;
    private Shape triangle;
    public Shape compute(String shapeName) {
        if (shapeName == null || shapeName.length() == 0) {
            throw new IllegalArgumentException("Not found " + shapeName);
        }

        if (shapeName.equals("circular")) {
            return circular;
        }

        if (shapeName.equals("rectangle")) {
            return rectangle;
        }

        if (shapeName.equals("triangle")) {
            return triangle;
        }

        throw new IllegalArgumentException("Not found " + shapeName);
    }

    public void setTriangle(Shape triangle) {
        this.triangle = triangle;
    }

    public void setRectangle(Shape rectangle) {
        this.rectangle = rectangle;
    }

    public void setCircular(Shape circular) {
        this.circular = circular;
    }
}