package com.fynnjason.sanews.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.fynnjason.sanews.R;

import java.util.List;

/**
 * 基于AndroidUtilCode权限申请封装，目的是简化代码，让权限申请更加简单
 * GitHub地址：https://github.com/Blankj/AndroidUtilCode
 * 使用该类前保证已依赖上面的工具库
 * 开发者：FynnJason
 */
public class PermissionHelper {

    // 只会在mustApply中使用
    public PermissionHelper(SimpleListener applyListener) {
        mSimpleListener = applyListener;
    }

    // 只会在mustApplyDiy使用
    public PermissionHelper(FullListener fullListener) {
        mFullListener = fullListener;
    }

    public interface SimpleListener {
        void success();
    }

    private SimpleListener mSimpleListener;

    public interface FullListener {
        void success();

        void fail();
    }

    private FullListener mFullListener;


    /**
     * 用户拒绝申请权限后，只提供失败回调，具体逻辑自己来写，目的是为了定制用户拒绝后的提示UI
     *
     * @param permissions
     */
    public void mustApplyDiy(@PermissionConstants.Permission final String... permissions) {
        PermissionUtils.permission(permissions)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(ShouldRequest shouldRequest) {
                        // 当用户第一次拒绝后再次申请权限就会进入这个回调
                        shouldRequest.again(true);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        // 当用户全部权限申请成功后进入这个回调，这里自己写一个回调，做其他逻辑操作
                        mFullListener.success();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        // 当用户拒绝申请权限时
                        // 如果用户多次拒绝申请，要让用户主动到设置界面去开启权限
                        if (!permissionsDeniedForever.isEmpty()) {
                            showOpenAppSettingDialog();
                        } else {
                            // 只提供失败回调，具体处理自己写
                            mFullListener.fail();
                        }
                    }
                })
                .request();
    }


    /**
     * 必须申请的单个或多个权限(方法自带拒绝后处理)
     * 用户如果拒绝申请，那就会弹出提示框，并要求用户重新申请；
     * 这种方法保证了权限能够申请，是比较推荐的方法；
     * 当然还有一种方式是，当用户使用的某个权限时再申请，不过这个逻辑需要开发者自己来实现；
     *
     * @param permissions
     */
    public void mustApply(@PermissionConstants.Permission final String... permissions) {
        PermissionUtils.permission(permissions)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(ShouldRequest shouldRequest) {
                        // 当用户第一次拒绝后再次申请权限就会进入这个回调
                        shouldRequest.again(true);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        // 当用户全部权限申请成功后进入这个回调，这里自己写一个回调，做其他逻辑操作
                        mSimpleListener.success();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        // 当用户拒绝申请权限时
                        // 如果用户多次拒绝申请，要让用户主动到设置界面去开启权限
                        if (!permissionsDeniedForever.isEmpty()) {
                            showOpenAppSettingDialog();
                        } else {
                            // 使用最简单的弹窗来提示用户
                            Activity topActivity = ActivityUtils.getTopActivity();
                            if (topActivity == null || topActivity.isFinishing()) return;
                            new AlertDialog.Builder(topActivity)
                                    .setTitle("应用权限申请")
                                    .setMessage("为了您能正常使用，请允许" + topActivity.getResources().getString(R.string.app_name) + "申请相应权限")
                                    .setCancelable(false)
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                            // 重新调用权限申请
                                            mustApply(permissions);
                                        }
                                    })
                                    .show();
                        }
                    }
                })
                .request();
    }


    /**
     * 申请单个或多个权限；
     * 用户如果拒绝申请权限，不做任何处理，这是最基本的权限申请，这种情况用的比较少；
     *
     * @param permissions 单个或多个权限
     */
    public void normalApply(@PermissionConstants.Permission final String... permissions) {
        PermissionUtils.permission(permissions)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(ShouldRequest shouldRequest) {

                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {

                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {

                    }
                }).request();
    }

    /**
     * 当用户多次拒绝申请权限，要让用户主动到设置界面中开启权限申请
     */
    private void showOpenAppSettingDialog() {
        Activity topActivity = ActivityUtils.getTopActivity();
        if (topActivity == null || topActivity.isFinishing()) return;
        new AlertDialog.Builder(topActivity)
                .setTitle("应用权限申请")
                .setMessage("您已拒绝申请权限，为了正常使用应用，请打开设置中权限管理开启对应权限")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        PermissionUtils.launchAppDetailsSettings();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }


}
