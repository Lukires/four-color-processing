# four-color-processing
Implementation of the Welsh Powell Algorithm in Processing 3 to color a Voronoi Diagram in 4 colors.

Implementation isn't perfect, the Voronoi Diagram could use a couple iterations of Lloyd Relaxation. The lack of relaxations means it doesn't follow exactly the specifications of the four color theorem. The point of this program is clearly presentation, so we cheat this by simply generating a new map behind the scenes, in the event that any issues happen.

## Missing mesh or quickhull?
Include the files from ./externalfiles in your library. Developed by http://leebyron.com/mesh/.
