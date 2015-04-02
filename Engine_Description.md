## Screen Size ##
Typical 'screen area' is 800x480 for phones, while tablets are 1280x800. The engine automatically accounts for screen size and adjusts things to fit.

![https://lh4.googleusercontent.com/-R2vUdDij6Ck/UHTHkbmC3dI/AAAAAAAAAac/zwVBN00GSiA/s800/Screen.png](https://lh4.googleusercontent.com/-R2vUdDij6Ck/UHTHkbmC3dI/AAAAAAAAAac/zwVBN00GSiA/s800/Screen.png)

## File Format ##
The game is made out of 2D sprite sheets, with support for transparency. Meaning supported formats are jpg, and png.

## Interaction ##
The main character's interaction surfaces are axis aligned bounding boxes. In short, the protagonist walks on flat floors and hits flat walls. Slopes, hills, angles, are currently not possible.

![https://lh4.googleusercontent.com/-bsAtPYvPFtM/UHTHkFDFhXI/AAAAAAAAAZo/dI6uAapCXY0/s800/bounding+boxes.png](https://lh4.googleusercontent.com/-bsAtPYvPFtM/UHTHkFDFhXI/AAAAAAAAAZo/dI6uAapCXY0/s800/bounding+boxes.png)

## Layers ##
There are 10 layers of sprites. 0-4 are foreground layers (objects are in front of main character). 5 is the main character and interaction layer, 6-10 are background layers (main character is in front of objects). Different layers can slide/move at different rates as well to simulate distance.

![https://lh4.googleusercontent.com/-i0R4WMxzlgU/UHTJN2qoc6I/AAAAAAAAAak/fksu7YZXdjU/s800/bounding+boxes+overlay+background.png](https://lh4.googleusercontent.com/-i0R4WMxzlgU/UHTJN2qoc6I/AAAAAAAAAak/fksu7YZXdjU/s800/bounding+boxes+overlay+background.png)

## Animation ##
Animation is accomplished by multiple frames on a sprite sheet. Any frames per second can be specified (eg .5fps, 200fps, etc) as well as any position/ordering of frames. Multiple animations can be stored in a single sprite sheet.

![https://lh4.googleusercontent.com/-K_sqN0diyCg/UHTNzwTBeaI/AAAAAAAAAa8/ky6u3i6uieA/s1000/animation.png](https://lh4.googleusercontent.com/-K_sqN0diyCg/UHTNzwTBeaI/AAAAAAAAAa8/ky6u3i6uieA/s1000/animation.png)

![https://lh3.googleusercontent.com/-AFpB2DtBJhQ/UHTNzuRulpI/AAAAAAAAAa4/fml3llzHwdM/s200/Untitled.gif](https://lh3.googleusercontent.com/-AFpB2DtBJhQ/UHTNzuRulpI/AAAAAAAAAa4/fml3llzHwdM/s200/Untitled.gif)

The XML to make an animation is as follows.
```
<frameAnimation>
   <animation_set>
      <entry>
         <!-- Animations go here -->
          
         <!-- What is this animation called? -->
         <enumGlobalAnimationList>stop</enumGlobalAnimationList>
         <frameSet>
            <!-- How many frames per second -->
            <rec_fps>1.0</rec_fps>
            <frame_list>
                
                <!-- Frames go here -->
 			<sprite n="frame_1.png" x="0" y="0" w="100" h="100"/>
            
            </frame_list>
         </frameSet>

      </entry>
   </animation_set>
</frameAnimation>
```

Which means you can use utilities like TexturePacker very easily to make animations.

![http://i.imgur.com/qJQDI.png](http://i.imgur.com/qJQDI.png)

## Cut Scenes ##
Handled by moving the animations along a vector in engine, making things easy and there no need for rendering outside of the game.

## Lights ##
  * Regular Lights act like a Multiply Layer in Photoshop or Gimp.
    * Alpha acts like brightness of light.
  * Bloom Lights act like an Overlay + Mulitply Layer in Photoshop or Gimp.
    * Alpha acts like regular image alpha.

(Image is best examined in an editor to understand alpha.)
![http://i.imgur.com/0k2qu.png](http://i.imgur.com/0k2qu.png)

  * Lights can overlap and blend correctly.

![http://i.imgur.com/a56TH.png](http://i.imgur.com/a56TH.png)