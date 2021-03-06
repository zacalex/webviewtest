package com.andorid.car.compat;

import static android.car.drivingstate.CarUxRestrictions.UX_RESTRICTIONS_LIMIT_STRING_LENGTH;

import android.car.Car;
import android.car.CarNotConnectedException;
import android.car.drivingstate.CarUxRestrictions;
import android.car.drivingstate.CarUxRestrictions.CarUxRestrictionsInfo;
import android.car.drivingstate.CarUxRestrictionsManager;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

import com.android.car.ui.R;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * Utility class to access Car Restriction Manager.
 *
 * <p>This class must be a singleton because only one listener can be registered with {@link
 * CarUxRestrictionsManager} at a time, as documented in {@link
 * CarUxRestrictionsManager#registerListener}.
 */
public class CarUxRestrictionsUtil {
    private static final String TAG = "CarUxRestrictionsUtil";

    private final Car mCarApi;
    private CarUxRestrictionsManager mCarUxRestrictionsManager;
    @NonNull
    private CarUxRestrictions mCarUxRestrictions = getDefaultRestrictions();

    private Set<OnUxRestrictionsChangedListener> mObservers;
    private static CarUxRestrictionsUtil sInstance = null;

    private CarUxRestrictionsUtil(Context context) {
        CarUxRestrictionsManager.OnUxRestrictionsChangedListener listener =
                (carUxRestrictions) -> {
                    if (carUxRestrictions == null) {
                        mCarUxRestrictions = getDefaultRestrictions();
                    } else {
                        mCarUxRestrictions = carUxRestrictions;
                    }

                    for (OnUxRestrictionsChangedListener observer : mObservers) {
                        observer.onRestrictionsChanged(mCarUxRestrictions);
                    }
                };

        mCarApi = Car.createCar(context.getApplicationContext());
        mObservers = Collections.newSetFromMap(new WeakHashMap<>());

        try {
            mCarUxRestrictionsManager =
                    (CarUxRestrictionsManager) mCarApi.getCarManager(
                            Car.CAR_UX_RESTRICTION_SERVICE);
            mCarUxRestrictionsManager.registerListener(listener);
            listener.onUxRestrictionsChanged(
                    mCarUxRestrictionsManager.getCurrentCarUxRestrictions());
        } catch (CarNotConnectedException | NullPointerException e) {
            Log.e(TAG, "Car not connected", e);
            // mCarUxRestrictions will be the default
        }
    }

    @NonNull
    private static CarUxRestrictions getDefaultRestrictions() {
        return new CarUxRestrictions.Builder(
                true, CarUxRestrictions.UX_RESTRICTIONS_FULLY_RESTRICTED, 0)
                .build();
    }

    /** Listener interface used to update clients on UxRestrictions changes */
    public interface OnUxRestrictionsChangedListener {
        /** Called when CarUxRestrictions changes */
        void onRestrictionsChanged(@NonNull CarUxRestrictions carUxRestrictions);
    }

    /** Returns the singleton sInstance of this class */
    @NonNull
    public static CarUxRestrictionsUtil getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CarUxRestrictionsUtil(context);
        }

        return sInstance;
    }

    /**
     * Registers a listener on this class for updates to CarUxRestrictions. Multiple listeners may
     * be registered. Note that this class will only hold a weak reference to the listener, you
     * must maintain a strong reference to it elsewhere.
     */
    public void register(OnUxRestrictionsChangedListener listener) {
        mObservers.add(listener);
        listener.onRestrictionsChanged(mCarUxRestrictions);
    }

    /** Unregisters a registered listener */
    public void unregister(OnUxRestrictionsChangedListener listener) {
        mObservers.remove(listener);
    }

    @NonNull
    public CarUxRestrictions getCurrentRestrictions() {
        return mCarUxRestrictions;
    }

    /**
     * Returns whether any of the given flags are blocked by the specified restrictions. If null is
     * given, the method returns true for safety.
     */
    public static boolean isRestricted(
            @CarUxRestrictionsInfo int restrictionFlags, @Nullable CarUxRestrictions uxr) {
        return (uxr == null) || ((uxr.getActiveRestrictions() & restrictionFlags) != 0);
    }

    /**
     * Complies the input string with the given UX restrictions. Returns the original string if
     * already compliant, otherwise a shortened ellipsized string.
     */
    public static String complyString(Context context, String str, CarUxRestrictions uxr) {

        if (isRestricted(UX_RESTRICTIONS_LIMIT_STRING_LENGTH, uxr)) {
            int maxLength =
                    uxr == null
                            ? context.getResources().getInteger(
                            R.integer.car_ui_default_max_string_length)
                            : uxr.getMaxRestrictedStringLength();

            if (str.length() > maxLength) {
                return str.substring(0, maxLength) + context.getString(R.string.car_ui_ellipsis);
            }
        }

        return str;
    }

    /** Sets car UX restrictions. Only used for testing. */
    @VisibleForTesting
    public void setUxRestrictions(CarUxRestrictions carUxRestrictions) {
        mCarUxRestrictions = carUxRestrictions;
    }
}

