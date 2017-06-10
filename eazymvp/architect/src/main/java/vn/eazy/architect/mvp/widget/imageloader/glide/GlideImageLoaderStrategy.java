package vn.eazy.architect.mvp.widget.imageloader.glide;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.eazy.architect.mvp.widget.imageloader.BaseImageLoaderStrategy;

/**
 * Created by harryle on 6/8/17.
 */

public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy<GlideImageConfig> {
    @Override
    public void loadImage(Context ctx, GlideImageConfig config) {
        if (ctx == null) throw new IllegalStateException("Context is required");
        if (config == null) throw new IllegalStateException("GlideImageConfig is required");
        if (TextUtils.isEmpty(config.getUrl()))
            throw new IllegalStateException(("Url is required"));
        if (config.getImageViews() == null)
            throw new IllegalStateException("ImageView is required");

        RequestManager manager;

        manager = Glide.with(ctx);

        DrawableRequestBuilder<String> requestBuilder = manager.load(config.getUrl())
                .crossFade().centerCrop();

        switch (config.getCacheStrategy()) {
            case 0:
                requestBuilder.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
            case 1:
                requestBuilder.diskCacheStrategy(DiskCacheStrategy.NONE);
                break;
            case 2:
                requestBuilder.diskCacheStrategy(DiskCacheStrategy.SOURCE);
                break;
            case 3:
                requestBuilder.diskCacheStrategy(DiskCacheStrategy.RESULT);
                break;
        }

        if (config.getTransformation() != null) {
            requestBuilder.transform(config.getTransformation());
        }

        if (config.getPlaceHolder() != 0) {
            requestBuilder.placeholder(config.getPlaceHolder());
        }

        if (config.getErrorPic() != 0) {
            requestBuilder.error(config.getPlaceHolder());
        }

        requestBuilder.into(config.getImageView());
    }

    @Override
    public void clear(final Context ctx, GlideImageConfig config) {
        if (ctx == null) throw new IllegalStateException("Context is required");
        if (config == null) throw new IllegalStateException("GlideImageConfig is required");

        if (config.getImageViews() != null && config.getImageViews().length > 0) {
            for (ImageView imageView : config.getImageViews()) {
                Glide.clear(imageView);
            }
        }

        if (config.getTargets() != null && config.getTargets().length > 0) {
            for (Target target : config.getTargets()) {
                Glide.clear(target);
            }
        }

        if (config.isClearDiskCache()) {
            Observable.just(0)
                    .observeOn(Schedulers.io())
                    .subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(@NonNull Integer integer) throws Exception {
                            Glide.get(ctx).clearDiskCache();
                        }
                    });
        }

        if (config.isClearMemory()) {
            Glide.get(ctx).clearMemory();
        }
    }
}
