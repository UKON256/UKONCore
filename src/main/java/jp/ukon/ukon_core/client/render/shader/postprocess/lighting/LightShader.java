package jp.ukon.ukon_core.client.render.shader.postprocess.lighting;

import java.util.ArrayList;
import java.util.List;

public class LightShader {
    public List<LightSource> AllLightSources;

    public LightShader() {
        AllLightSources = new ArrayList<>();
    }

    public void addLight(LightSource light) {
        this.AllLightSources.add(light);
    }
}
