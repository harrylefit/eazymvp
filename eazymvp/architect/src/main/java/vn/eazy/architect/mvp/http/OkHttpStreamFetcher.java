package vn.eazy.architect.mvp.http;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;

import java.io.InputStream;

/**
 * Created by harryle on 6/10/17.
 */

public class OkHttpStreamFetcher implements DataFetcher<InputStream>{
    @Override
    public InputStream loadData(Priority priority) throws Exception {
        return null;
    }

    @Override
    public void cleanup() {

    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void cancel() {

    }
}
