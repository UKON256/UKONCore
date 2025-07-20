package jp.ukon.ukon_core.client.render.shader.postprocess;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public abstract class PostProcessEffect<T extends PostProcessEffect<?>> {
    /*private static final FloatBuffer FLOAT_BUFFER_1 = BufferUtils.createFloatBuffer(1);
    private static final FloatBuffer FLOAT_BUFFER_2 = BufferUtils.createFloatBuffer(2);
    private static final FloatBuffer FLOAT_BUFFER_3 = BufferUtils.createFloatBuffer(3);
    private static final FloatBuffer FLOAT_BUFFER_4 = BufferUtils.createFloatBuffer(4);
    private static final IntBuffer INT_BUFFER_1 = BufferUtils.createIntBuffer(1);
    private static final IntBuffer INT_BUFFER_2 = BufferUtils.createIntBuffer(2);
    private static final IntBuffer INT_BUFFER_3 = BufferUtils.createIntBuffer(3);
    private static final IntBuffer INT_BUFFER_4 = BufferUtils.createIntBuffer(4);

    public T init() {
        return (T) this;
    }

    protected abstract ResourceLocation[] getShaders();

    protected void uploadFloat(int uniform, float... values) {
        if (uniform < 0)
            return;
        switch (values.length) {
            default:
            case 1:
                this.setFloats(FLOAT_BUFFER_1, values);
                GlStateManager._glUniform1(uniform, FLOAT_BUFFER_1);
                break;
            case 2:
                this.setFloats(FLOAT_BUFFER_2, values);
                GlStateManager._glUniform1(uniform, FLOAT_BUFFER_2);
                break;
            case 3:
                this.setFloats(FLOAT_BUFFER_3, values);
                GlStateManager._glUniform1(uniform, FLOAT_BUFFER_3);
                break;
            case 4:
                this.setFloats(FLOAT_BUFFER_4, values);
                GlStateManager._glUniform1(uniform, FLOAT_BUFFER_4);
                break;
        }
    }

    private void setFloats(FloatBuffer buffer, float[] values) {
        buffer.position(0);
        for (float value : values)
            buffer.put(value);
        buffer.flip();
    }

    protected final void uploadInt(int uniform, int... values) {
        if(uniform < 0)
            return;
        switch(values.length) {
            default:
            case 1:
                this.setInts(INT_BUFFER_1, values);
                GlStateManager._glUniform1(uniform, INT_BUFFER_1);
                break;
            case 2:
                this.setInts(INT_BUFFER_2, values);
                GlStateManager._glUniform2(uniform, INT_BUFFER_2);
                break;
            case 3:
                this.setInts(INT_BUFFER_3, values);
                GlStateManager._glUniform3(uniform, INT_BUFFER_3);
                break;
            case 4:
                this.setInts(INT_BUFFER_4, values);
                GlStateManager._glUniform4(uniform, INT_BUFFER_4);
                break;
        }
    }

    private void setInts(IntBuffer buffer, int[] values) {
        buffer.position(0);
        for (int value : values)
            buffer.put(value);
        buffer.flip();
    }

    protected final int getUniform(String name) {
        return GlStateManager._glGetUniformLocation(this.getShaderProgram(), name);
    }*/
}
