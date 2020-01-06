LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_USE_AAPT2 := true

LOCAL_MODULE_TAGS := optional

LOCAL_PRIVATE_PLATFORM_APIS := true

LOCAL_JAVA_VERSION := 1.8

LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res

LOCAL_SRC_FILES := \
        $(call all-java-files-under, src)


LOCAL_PACKAGE_NAME := CarCompat

LOCAL_DEX_PREOPT := false

LOCAL_PRIVILEGED_MODULE := true

LOCAL_CERTIFICATE := platform

LOCAL_STATIC_ANDROID_LIBRARIES := \
    car-ui-lib

LOCAL_STATIC_JAVA_LIBRARIES := \
    android.car.userlib \
		junit \
    vehicle-hal-support-lib \


LOCAL_JAVA_LIBRARIES := \
	android.test.runner.stubs \
	android.test.base.stubs \
	android.car \

LOCAL_PROGUARD_ENABLED := disabled

include $(BUILD_PACKAGE)
