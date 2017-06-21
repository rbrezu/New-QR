import com.google.zxing.BinaryBitmap;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import ro.whitesoftware.qr.detector.FinderPatternFinder;
import ro.whitesoftware.qr.detector.FinderPatternInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by daedal on 21.06.2017.
 */
public class Main {

    public static void main(String[] args) throws IOException, NotFoundException {
        File img = new File("/home/daedal/work/QR/download.png");
        BufferedImage bufferedImage = ImageIO.read(img);

        int[] pixels = bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null, 0, bufferedImage.getWidth());
        RGBLuminanceSource source = new RGBLuminanceSource(bufferedImage.getWidth(), bufferedImage.getHeight(), pixels);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        BitMatrix bitMatrix = bitmap.getBlackMatrix();
        FinderPatternFinder finder = new FinderPatternFinder(
                bitMatrix
        );
        FinderPatternInfo info = finder.find(null);

        final BufferedImage bimage = ImageIO.read(img);
        Graphics2D g2d = bimage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.red);

        float estimatedSize = info.getLeft().getEstimatedModuleSize();
        g2d.setStroke(new BasicStroke(estimatedSize));

        g2d.fill(new Ellipse2D.Double(info.getLeft().getX() - estimatedSize / 2, info.getLeft().getY() - estimatedSize / 2, estimatedSize, estimatedSize));

        estimatedSize = info.getLeft().getEstimatedModuleSize() * 4f;
        g2d.draw(new Ellipse2D.Double(info.getLeft().getX() - estimatedSize / 2, info.getLeft().getY() - estimatedSize / 2, estimatedSize, estimatedSize));

        g2d.dispose();

        File out = new File("/home/daedal/work/QR/out.png");
        ImageIO.write(bimage, "png", out);

        JFrame jf = new JFrame("Demo");
        Container cp = jf.getContentPane();
        cp.add(new JComponent() {
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                AffineTransform transform =  new AffineTransform();
                transform.scale(3f, 3f);

                g2.drawRenderedImage(bimage, transform);
            }
        });
        jf.setSize(bimage.getWidth() * 3, bimage.getHeight() * 3);
        jf.setVisible(true);
    }

}
