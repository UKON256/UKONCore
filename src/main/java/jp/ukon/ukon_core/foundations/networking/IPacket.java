package jp.ukon.ukon_core.foundations.networking;

public interface IPacket {
    public abstract void write();
    public abstract void handle();
}
