package vn.eazy.architect.mvp.widget.imageloader;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by harryle on 6/7/17.
 */
@Singleton
public class ImageLoader {
    private BaseImageLoaderStrategy mStrategy;

    @Inject
    public ImageLoader(BaseImageLoaderStrategy strategy) {
        setLoadImgStrategy(strategy);
    }

    public <T extends ImageConfig> void loadImage(Context ctx, T config) {
        this.mStrategy.loadImage(ctx, config);
    }

    public <T extends ImageConfig> void clear(Context ctx, T config) {
        this.mStrategy.clear(ctx, config);
    }

    public void setLoadImgStrategy(BaseImageLoaderStrategy strategy) {
        this.mStrategy = strategy;
    }
}
