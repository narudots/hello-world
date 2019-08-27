package utils.TestOutputReportingUtils;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;

import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import org.monte.media.FormatKeys.MediaType;
import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoCapture {
	
	private static ScreenRecorder	screenRecorder ;
	public static void stopRecording() throws Exception {
	    screenRecorder.stop();
	}
	public static void startRecording() throws Exception {
	 
	    // set the graphics configuration
	    GraphicsConfiguration gc = GraphicsEnvironment
	            .getLocalGraphicsEnvironment()
	            .getDefaultScreenDevice()
	            .getDefaultConfiguration();
	         
	    // initialize the screen recorder:
	    // - default graphics configuration
	    // - full screen recording
	    // - record in AVI format
	    // - 15 frames per second
	    // - black mouse pointer
	    // - no audio
	    // - save capture to predefined location
	    screenRecorder = new ScreenRecorder(gc,
	            gc.getBounds(),
	            new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
	            new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
	                    CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
	                    DepthKey, 24, FrameRateKey, Rational.valueOf(15),
	                    QualityKey, 1.0f,
	                    KeyFrameIntervalKey, 15 * 60),
	            new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
	            null,
	            new File("C:\\Users\\nsakthivel\\Documents\\Narmada Files\\Selenium"));
	         screenRecorder.start();
	}	
	
}