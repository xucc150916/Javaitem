package com.xucc.spring1.xml;

import com.xucc.spring1.common.Shape;

/**
 * 通过传入不同参数(图形名)进行相关操作
 */

public class XmlShapeCompute {
    private Shape circular;
    private Shape rectangle;
    private Shape triangle;
    public Shape compute(String shapeName) {
        if (shapeName == null || shapeName.length() == 0) {
            throw new IllegalArgumentException("Not found " + shapeName);
        }

        // 根据输入的图形名，返回不同的对象
        if (shapeName.equals("circular")) {
            return circular;
        } else if (shapeName.equals("rectangle")) {
            return rectangle;
        } else if (shapeName.equals("triangle")) {
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