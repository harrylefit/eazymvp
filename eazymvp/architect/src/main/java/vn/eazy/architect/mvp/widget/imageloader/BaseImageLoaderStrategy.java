package vn.eazy.architect.mvp.widget.imageloader;

import android.content.Context;

/**
 * Created by harryle on 6/7/17.
 */

public interface BaseImageLoaderStrategy<T extends ImageConfig> {
    void loadImage(Context ctx, T config);

    void clear(Context ctx, T config);
}
