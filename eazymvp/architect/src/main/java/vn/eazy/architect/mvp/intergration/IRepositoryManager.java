package vn.eazy.architect.mvp.intergration;

/**
 * Created by harryle on 6/7/17.
 */

public interface IRepositoryManager {
    /**
     *
     * @param services
     */
    void injectRetrofitService(Class<?>... services);

    /**
     *
     * @param services
     */
    void injectCacheService(Class<?>... services);

    /**
     *
     * @param services
     * @param <T>
     * @return
     */
    <T> T obtainRetrofitService(Class<T> services);

    /**
     *
     * @param cache
     * @param <T>
     * @return
     */
    <T> T obtainCacheService(Class<T> cache);
}
