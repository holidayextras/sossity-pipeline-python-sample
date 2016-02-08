package com.acacia.pythonpipeline;


import com.acacia.sdk.AbstractTransform;
import com.acacia.sdk.GenericDataflowAppException;
import org.python.core.PyException;

public class Main {


    public static void main(String[] args) throws PyException {

        PythonComposer c = new PythonComposer();

        for(AbstractTransform a : c.getOrderedTransforms()){

            try {
                String s = a.transform("{\"field1\":\"value1\"}");
                System.out.println(s);
            } catch (GenericDataflowAppException e) {
                e.printStackTrace();
            }

        }


    }


}
