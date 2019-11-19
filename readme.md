# JVid2Mc

## How 2 use - quick and dirty

+ run jar
+ choose video file (mp4 only)
+ if needed, change id (this will be the name that is used in minecraft later)
+ adjust settings
  + pixel is resolution of minecraft video
  + blocks is video size in minecraft blocks (untested)
  + pixelsize is size of each pixel (untested)
  + fps is... fps. limited to 20
+ set background color (color to be left out in video. select green for greenscreen videos f.ex.)
+ background threshold is distance to defined background color so "close" colors are left out as well
  + threshold of 0 means exact color, 200 means difference of 200 in rgb value of background to actual pixel rgb
+ run it. wait some time. wait some more time. (there is no feedback when it's done yet)
+ copy generated data pack to minecraft world folder
+ run minecraft and open world with datapack
+ summon a videoplayer with /function {id}:place ({id} is id from step 3)
+ remove closest videoplayer with /function {id}:remove
+ start closest videoplayer with /function {id}:start
+ stop closest videoplayer with /function {id}:stop