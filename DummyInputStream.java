package Model;

import java.io.IOException;
import java.io.InputStream;

public class DummyInputStream extends InputStream {
	private byte[] data;
    private int position;

    public DummyInputStream(byte[] data) {
        this.data = data;
        this.position = 0;
    }
    @Override
    public int read() throws IOException {
        if (position >= data.length) {
            return -1;  // End of stream
        }
        return data[position++];
    }
}
