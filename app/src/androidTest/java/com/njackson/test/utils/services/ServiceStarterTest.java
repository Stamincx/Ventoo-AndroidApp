package com.njackson.test.utils.services;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.njackson.activityrecognition.ActivityRecognitionServiceCommand;
import com.njackson.fit.GoogleFitServiceCommand;
import com.njackson.gps.GPSServiceCommand;
import com.njackson.live.LiveServiceCommand;
import com.njackson.oruxmaps.OruxMaps;
import com.njackson.oruxmaps.OruxMapsServiceCommand;
import com.njackson.pebble.PebbleServiceCommand;
import com.njackson.utils.services.ServiceStarter;

import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by njackson on 03/01/15.
 */
public class ServiceStarterTest extends AndroidTestCase {

    Context _mockContext;
    private ServiceStarter _serviceStarter;
    private SharedPreferences _mockPreferences;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        System.setProperty("dexmaker.dexcache", getContext().getCacheDir().getPath());
        setupMocks();

        _serviceStarter = new ServiceStarter(_mockContext, _mockPreferences);
    }

    private void setupMocks() {
        _mockContext = mock(Context.class);
        _mockPreferences = mock(SharedPreferences.class);

        when(_mockPreferences.getString("REFRESH_INTERVAL","1000")).thenReturn("1500");
    }

    @SmallTest
    public void testStartsGPSService() throws Exception {
        _serviceStarter.startLocationServices();
        ArgumentCaptor<Intent> intentArgumentCaptor = ArgumentCaptor.forClass(Intent.class);

        verify(_mockContext,times(4)).startService(intentArgumentCaptor.capture());

        List<Intent> intents = intentArgumentCaptor.getAllValues();
        assertTrue(checkComponentInCaptor(intents, GPSServiceCommand.class));
    }

    @SmallTest
    public void testStartsGPSServiceWithRefreshInterval() throws Exception {
        _serviceStarter.startLocationServices();
        ArgumentCaptor<Intent> intentArgumentCaptor = ArgumentCaptor.forClass(Intent.class);

        verify(_mockContext,times(4)).startService(intentArgumentCaptor.capture());

        List<Intent> intents = intentArgumentCaptor.getAllValues();
        Intent startIntent = getIntentFromCaptor(intents,GPSServiceCommand.class);

        int refreshInterval = startIntent.getIntExtra("REFRESH_INTERVAL",1000);
        assertEquals(1500,refreshInterval);
    }

    @SmallTest
    public void testStopsGPSService() throws Exception {
        _serviceStarter.stopLocationServices();
        ArgumentCaptor<Intent> intentArgumentCaptor = ArgumentCaptor.forClass(Intent.class);

        verify(_mockContext,times(4)).stopService(intentArgumentCaptor.capture());

        List<Intent> intents = intentArgumentCaptor.getAllValues();
        assertTrue(checkComponentInCaptor(intents,GPSServiceCommand.class));
    }

    @SmallTest
    public void testStartsPebbleBikeService() throws Exception {
        _serviceStarter.startPebbleServices();
        ArgumentCaptor<Intent> intentArgumentCaptor = ArgumentCaptor.forClass(Intent.class);

        verify(_mockContext,times(1)).startService(intentArgumentCaptor.capture());

        List<Intent> intents = intentArgumentCaptor.getAllValues();
        assertTrue(checkComponentInCaptor(intents,PebbleServiceCommand.class));
    }

    @SmallTest
    public void testStopsPebbleBikeService() throws Exception {
        _serviceStarter.stopPebbleServices();
        ArgumentCaptor<Intent> intentArgumentCaptor = ArgumentCaptor.forClass(Intent.class);

        verify(_mockContext,times(1)).stopService(intentArgumentCaptor.capture());

        List<Intent> intents = intentArgumentCaptor.getAllValues();
        assertTrue(checkComponentInCaptor(intents,PebbleServiceCommand.class));
    }

    @SmallTest
    public void testStartsActivityRecognitionService() throws Exception {
        _serviceStarter.startActivityServices();
        ArgumentCaptor<Intent> intentArgumentCaptor = ArgumentCaptor.forClass(Intent.class);

        verify(_mockContext,times(1)).startService(intentArgumentCaptor.capture());

        List<Intent> intents = intentArgumentCaptor.getAllValues();
        assertTrue(checkComponentInCaptor(intents,ActivityRecognitionServiceCommand.class));
    }

    @SmallTest
    public void testStopsActivityRecognitionService() throws Exception {
        _serviceStarter.stopActivityServices();
        ArgumentCaptor<Intent> intentArgumentCaptor = ArgumentCaptor.forClass(Intent.class);

        verify(_mockContext,times(1)).stopService(intentArgumentCaptor.capture());

        List<Intent> intents = intentArgumentCaptor.getAllValues();
        assertTrue(checkComponentInCaptor(intents,ActivityRecognitionServiceCommand.class));
    }

    @SmallTest
    public void testStartsLiveTrackingService() throws Exception {
        _serviceStarter.startLocationServices();
        ArgumentCaptor<Intent> intentArgumentCaptor = ArgumentCaptor.forClass(Intent.class);

        verify(_mockContext,times(4)).startService(intentArgumentCaptor.capture());

        List<Intent> intents = intentArgumentCaptor.getAllValues();
        assertTrue(checkComponentInCaptor(intents,LiveServiceCommand.class));
    }

    @SmallTest
    public void testStopsLiveTrackingService() throws Exception {
        _serviceStarter.stopLocationServices();
        ArgumentCaptor<Intent> intentArgumentCaptor = ArgumentCaptor.forClass(Intent.class);

        verify(_mockContext,times(4)).stopService(intentArgumentCaptor.capture());

        List<Intent> intents = intentArgumentCaptor.getAllValues();
        assertTrue(checkComponentInCaptor(intents,LiveServiceCommand.class));
    }

    @SmallTest
    public void testStartsOruxService() throws Exception {
        _serviceStarter.startLocationServices();
        ArgumentCaptor<Intent> intentArgumentCaptor = ArgumentCaptor.forClass(Intent.class);

        verify(_mockContext,times(4)).startService(intentArgumentCaptor.capture());

        List<Intent> intents = intentArgumentCaptor.getAllValues();
        assertTrue(checkComponentInCaptor(intents,OruxMapsServiceCommand.class));
    }

    @SmallTest
    public void testStopsOruxService() throws Exception {
        _serviceStarter.stopLocationServices();
        ArgumentCaptor<Intent> intentArgumentCaptor = ArgumentCaptor.forClass(Intent.class);

        verify(_mockContext,times(4)).stopService(intentArgumentCaptor.capture());

        List<Intent> intents = intentArgumentCaptor.getAllValues();
        assertTrue(checkComponentInCaptor(intents,OruxMapsServiceCommand.class));
    }

    @SmallTest
    public void testStartsRecognitionService() throws Exception {
        _serviceStarter.startActivityServices();
        ArgumentCaptor<Intent> intentArgumentCaptor = ArgumentCaptor.forClass(Intent.class);

        verify(_mockContext,times(1)).startService(intentArgumentCaptor.capture());

        List<Intent> intents = intentArgumentCaptor.getAllValues();
        assertTrue(checkComponentInCaptor(intents,ActivityRecognitionServiceCommand.class));
    }

    @SmallTest
    public void testStopsRecognitionService() throws Exception {
        _serviceStarter.stopActivityServices();
        ArgumentCaptor<Intent> intentArgumentCaptor = ArgumentCaptor.forClass(Intent.class);

        verify(_mockContext,times(1)).stopService(intentArgumentCaptor.capture());

        List<Intent> intents = intentArgumentCaptor.getAllValues();
        assertTrue(checkComponentInCaptor(intents,ActivityRecognitionServiceCommand.class));
    }

    @SmallTest
    public void testStartsGoogleFitService() throws Exception {
        _serviceStarter.startLocationServices();
        ArgumentCaptor<Intent> intentArgumentCaptor = ArgumentCaptor.forClass(Intent.class);

        verify(_mockContext,times(4)).startService(intentArgumentCaptor.capture());

        List<Intent> intents = intentArgumentCaptor.getAllValues();
        assertTrue(checkComponentInCaptor(intents,GoogleFitServiceCommand.class));
    }

    @SmallTest
    public void testStopsGoogleFitService() throws Exception {
        _serviceStarter.stopLocationServices();
        ArgumentCaptor<Intent> intentArgumentCaptor = ArgumentCaptor.forClass(Intent.class);

        verify(_mockContext,times(4)).stopService(intentArgumentCaptor.capture());

        List<Intent> intents = intentArgumentCaptor.getAllValues();
        assertTrue(checkComponentInCaptor(intents,GoogleFitServiceCommand.class));
    }

    @SmallTest
    public void testServiceRunningReturnsTrueWhenServiceStarted() throws Exception {
        ActivityManager manager = mock(ActivityManager.class);
        ArrayList<ActivityManager.RunningServiceInfo> runningServices = new ArrayList<ActivityManager.RunningServiceInfo>();
        ActivityManager.RunningServiceInfo info = new ActivityManager.RunningServiceInfo();
        info.service = new ComponentName(GPSServiceCommand.class.getPackage().getName(),GPSServiceCommand.class.getName());
        runningServices.add(info);

        when(manager.getRunningServices(Integer.MAX_VALUE)).thenReturn(runningServices);
        when(_mockContext.getSystemService(getContext().ACTIVITY_SERVICE)).thenReturn(manager);

        assertEquals(true,_serviceStarter.serviceRunning(GPSServiceCommand.class));
    }

    @SmallTest
    public void testServiceRunningReturnsFalseWhenServiceNotRunning() throws Exception {
        ActivityManager manager = mock(ActivityManager.class);
        ArrayList<ActivityManager.RunningServiceInfo> runningServices = new ArrayList<ActivityManager.RunningServiceInfo>();
        ActivityManager.RunningServiceInfo info = new ActivityManager.RunningServiceInfo();
        info.service = new ComponentName(GPSServiceCommand.class.getPackage().getName(),GPSServiceCommand.class.getName());
        runningServices.add(info);

        when(manager.getRunningServices(Integer.MAX_VALUE)).thenReturn(runningServices);
        when(_mockContext.getSystemService(getContext().ACTIVITY_SERVICE)).thenReturn(manager);

        assertEquals(false,_serviceStarter.serviceRunning(OruxMaps.class));
    }

    private Intent getIntentFromCaptor(List<Intent> intents, Class<?> serviceClass) {
        for(Intent intent : intents) {
            if(intent.getComponent().getClassName().compareTo(serviceClass.getName()) == 0) {
                return intent;
            }
        }

        return null;
    }

    private boolean checkComponentInCaptor(List<Intent> intents, Class<?> serviceClass) {
        return (getIntentFromCaptor(intents,serviceClass) != null);
    }

}
