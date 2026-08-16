Automated Splash Screen for android apps.

Note:   This project is under development.
        You can help dev. in bug fixes and with your awesome ideas for Universal. SplashScreens

12 Splash Screen animation styles are included out of the box, from the original
TerminalAnimation (still awesome) to fades, zooms, curtains, circular reveals, loaders,
bouncing dots, a typewriter effect, a gradient wave and a ripple.

### Download

The library is built with the latest Gradle (8.14.3) and Android Gradle Plugin (8.7.3),
targets `compileSdk`/`targetSdk` 35 and uses AndroidX. JCenter/Bintray has been shut down,
so the artifact is distributed via [JitPack](https://jitpack.io) instead.

Add the JitPack repository in your root `settings.gradle`:

```groovy
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Then add the dependency to your module's `build.gradle`:

```groovy
dependencies {
    implementation 'com.github.manindertaggar:SplashAnimations:1.0.0'
}
```

### Usage
in onCreate method of your very first activity

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //add this Line 
        SplashScreen.show(this, SplashScreen.TERMINAL_ANIMATION);
        setContentView(R.layout.layout_content_view);
   ...
   ```
   That's it.
   You are all done.
   Now everytime this activity runs, a Splash Screen will run.

You can also be notified when the animation finishes:

```java
    SplashScreen.show(this, SplashScreen.RIPPLE_ANIMATION, () -> {
        // splash overlay has been removed
    });
```

### Available animations

| Constant                              | Description                                                   |
|----------------------------------------|----------------------------------------------------------------|
| `SplashScreen.TERMINAL_ANIMATION`       | Terminal boot sequence with a blinking cursor that expands and opens two flaps |
| `SplashScreen.FADE_ANIMATION`           | Simple cross-fade in and out                                   |
| `SplashScreen.ZOOM_ANIMATION`           | Logo scales in with an overshoot bounce, then zooms out         |
| `SplashScreen.SLIDE_UP_ANIMATION`       | A full panel slides upward off-screen like a rising curtain     |
| `SplashScreen.CURTAIN_ANIMATION`        | Two panels part from the center like theater curtains           |
| `SplashScreen.CIRCULAR_REVEAL_ANIMATION`| A colored overlay shrinks away using a circular reveal           |
| `SplashScreen.PULSE_ANIMATION`          | A circle pulses a few times before fading out                   |
| `SplashScreen.ROTATE_LOADER_ANIMATION`  | A spinner bar rotates a few times before fading out              |
| `SplashScreen.BOUNCE_DOTS_ANIMATION`    | Three dots bounce in a staggered loading sequence                |
| `SplashScreen.TYPEWRITER_ANIMATION`     | Text types out character by character next to a blinking cursor |
| `SplashScreen.GRADIENT_WAVE_ANIMATION`  | The background cycles through a sequence of colors               |
| `SplashScreen.RIPPLE_ANIMATION`         | Concentric rings ripple outward from the center                  |

The sample app in `/app` includes a button for every animation so you can preview them
all on a device or emulator.

## Upcoming Features:
 -Passing any custom layout to set it as Splash Screen.</br>
 -Custom stoping of SplashScreen.</br>

![Terminal](https://github.com/manindersingh030/SplashAnimations/blob/master/terminal.gif?raw=true)
