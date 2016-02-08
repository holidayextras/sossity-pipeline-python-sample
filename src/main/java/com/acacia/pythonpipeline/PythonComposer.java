package com.acacia.pythonpipeline;

import com.acacia.sdk.AbstractTransform;
import com.acacia.sdk.AbstractTransformComposer;
import com.google.auto.service.AutoService;

import java.util.ArrayList;
import java.util.List;


@AutoService(AbstractTransformComposer.class)
public class PythonComposer extends AbstractTransformComposer {

    List<AbstractTransform> transforms = new ArrayList<>();


    public PythonComposer(){
        super();
        transforms.add(new PythonPipeline("timestamp", "/timestamp.py", "append_date_to_map"));

    }

    @Override
    public List<AbstractTransform> getOrderedTransforms() {
        return transforms;
    }
}
