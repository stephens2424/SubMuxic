package com.gloxandro.submuxic.util.compat;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.mediarouter.media.MediaRouter;

import com.gloxandro.submuxic.domain.MusicDirectory;
import com.gloxandro.submuxic.service.DownloadFile;

import java.util.List;

public abstract class RemoteControlClientBase {
	
	public static RemoteControlClientBase createInstance() {
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			return new RemoteControlClientLP();
		} else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
			return new RemoteControlClientJB();
		} else {
			return new RemoteControlClientICS();
		}
	}
	
	protected RemoteControlClientBase() {
		// Avoid instantiation
	}
	
	public abstract void register(final Context context, final ComponentName mediaButtonReceiverComponent);
	public abstract void unregister(final Context context);
	public abstract void setPlaybackState(int state, int index, int queueSize);
	public abstract void updateMetadata(Context context, MusicDirectory.Entry currentSong);
	public abstract void metadataChanged(MusicDirectory.Entry currentSong);
	public abstract void updateAlbumArt(MusicDirectory.Entry currentSong, Bitmap bitmap);
	public abstract void registerRoute(MediaRouter router);
	public abstract void unregisterRoute(MediaRouter router);
	public abstract void updatePlaylist(List<DownloadFile> playlist);
}