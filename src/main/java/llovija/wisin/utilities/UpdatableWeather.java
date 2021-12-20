package llovija.wisin.utilities;

/**
 * Created by Victor on 18/04/2015.
 */
public interface UpdatableWeather {

    /**
     * When the NetworkReceiver state changes calls to this method
     *
     * @param status true if it's connected,  false if it's disconnected
     */

    void onUpdateWeather(boolean status);
}
