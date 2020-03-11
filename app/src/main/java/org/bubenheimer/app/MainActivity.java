package org.bubenheimer.app;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

public final class MainActivity extends Activity {
    private static final int RC = 123456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);
    }

    public void startFGSvc(View button) {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            reallyStartFGSvc();
        } else {
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, RC);
        }
    }

    public void reallyStartFGSvc() {
        startForegroundService(new Intent(this, FGSvc.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == RC && grantResults.length >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                reallyStartFGSvc();
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
