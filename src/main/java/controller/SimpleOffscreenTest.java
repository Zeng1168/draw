package controller;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

//stuff for JAVA3D


public class SimpleOffscreenTest extends JFrame {



    static final int WIDTH = 640;

    static final int HEIGHT = 480;

    ImageComponent2D buffer;

    Canvas3D canvas3D;

    Canvas3D onscreenCanvas3D;

    BorderLayout borderLayout = new BorderLayout();

    JButton renderButton = new JButton();

    JLabel imageLabel = new JLabel();
    //将渲染出的image存在这里
    BufferedImage drawImae=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_3BYTE_BGR);

    public BufferedImage getDrawImae() {
        return drawImae;
    }

    public void setDrawImae(BufferedImage drawImae) {
        this.drawImae = drawImae;
    }

    public SimpleOffscreenTest() {

        super();

        createOffScreenImage();

        try {

            initGUI();

        } catch (Exception e) {

            System.out.println("Exception in GUI caused");

            e.printStackTrace();

        }

    }

    void createOffScreenImage() {

        canvas3D = getOffScreenCanvas();

        onscreenCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

        SimpleUniverse u = new SimpleUniverse(onscreenCanvas3D);
        u.getViewingPlatform().setNominalViewingTransform();
        BranchGroup bg = createSceneGraph();
        u.addBranchGraph(bg);
        u.getViewer().getView().addCanvas3D(canvas3D);
        createBufferImage();

        canvas3D.setOffScreenBuffer(buffer);//此处buffer始终保持一个对象。

        canvas3D.renderOffScreenBuffer();

        canvas3D.waitForOffScreenRendering();

        imageLabel.setIcon(new ImageIcon(buffer.getImage()));//渲染完后可直接从何buffer取出bufferedimage
        setDrawImae(buffer.getImage());
        System.out.println("Frame: " + canvas3D.getView().getFrameNumber());

    }



    Canvas3D getOffScreenCanvas() {

        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

        Canvas3D c = new Canvas3D(config, true);

        Screen3D scr = c.getScreen3D();

        scr.setSize(new Dimension(1280, 1024));

        scr.setPhysicalScreenWidth(0.36124);

        scr.setPhysicalScreenHeight(0.28899555);

        return c;

    }



    void createBufferImage() {

        BufferedImage offScreenImg = new BufferedImage(WIDTH, WIDTH,

                BufferedImage.TYPE_INT_RGB);

        buffer = new ImageComponent2D(ImageComponent.FORMAT_RGB, offScreenImg,true, false);

        buffer.setCapability(ImageComponent2D.ALLOW_IMAGE_READ);

    }


    BranchGroup createSceneGraph() {

// Create the root of the branch graph

        BranchGroup objRoot = new BranchGroup();

// rotate object has composited transformation matrix

        Transform3D rotate = new Transform3D();

        Transform3D tempRotate = new Transform3D();

        rotate.rotX(Math.PI / 4.0d);

        tempRotate.rotY(Math.PI / 5.0d);

        rotate.mul(tempRotate);

        TransformGroup objRotate = new TransformGroup(rotate);



// Create the transform group node and initialize it to the

// identity. Enable the TRANSFORM_WRITE capability so that

        // our behavior code can modify it at runtime. Add it to the

        // root of the subgraph.

        TransformGroup objSpin = new TransformGroup();

        objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);



        objRoot.addChild(objRotate);

        objRotate.addChild(objSpin);



// Create a simple shape leaf node, add it to the scene graph.

        // ColorCube is a Convenience Utility class

        objSpin.addChild(new ColorCube(0.4));



        // Create a new Behavior object that will perform the desired

// operation on the specified transform object and add it into

// the scene graph.

        Transform3D yAxis = new Transform3D();

        Alpha rotationAlpha = new Alpha(-1, 4000);



        RotationInterpolator rotator = new RotationInterpolator(rotationAlpha,

                objSpin, yAxis, 0.0f, (float) Math.PI * 2.0f);



        // a bounding sphere specifies a region a behavior is active

// create a sphere centered at the origin with radius of 1

        BoundingSphere bounds = new BoundingSphere();

        rotator.setSchedulingBounds(bounds);
        objSpin.addChild(rotator);
        return objRoot;

    }

    private void initGUI() throws Exception {

        renderButton.setText("Render");

        renderButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (e.getActionCommand() == "Render") {

                    canvas3D.renderOffScreenBuffer();

                    canvas3D.waitForOffScreenRendering();

                    imageLabel.setIcon(new ImageIcon(buffer.getImage()));
                    setDrawImae(buffer.getImage());

                    repaint();

                    System.out.println("Frame: "

                            + canvas3D.getView().getFrameNumber());

                }

            }

        });

        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.getContentPane().setLayout(borderLayout);

        this.getContentPane().add(renderButton, BorderLayout.SOUTH);

        this.getContentPane().add(onscreenCanvas3D, BorderLayout.CENTER);

        this.getContentPane().add(imageLabel, BorderLayout.NORTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        onscreenCanvas3D.setSize(200, 200);

        this.pack();

        this.validate();

        this.repaint();

        this.show();

    }

}
