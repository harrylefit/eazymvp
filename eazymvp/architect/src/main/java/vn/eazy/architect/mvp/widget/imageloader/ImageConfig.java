package vn.eazy.architect.mvp.widget.imageloader;

import android.widget.ImageView;

/**
 * Created by harryle on 6/7/17.
 */

public class ImageConfig {
    protected String url;
    protected ImageView imageView;
    protected int placeHolder;
    protected int errorPic;

    public String getUrl() {
        return url;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public int getErrorPic() {
        return errorPic;
    }
}
