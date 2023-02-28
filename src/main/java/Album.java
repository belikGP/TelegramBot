import java.util.ArrayList;

public class Album {
    private ArrayList<String> photos;
    Album()
    {
        photos = new ArrayList<>();
        photos.add("C:\\Users\\Dell\\Desktop\\SlippingDmitry\\VIKpP_VMR68.jpg");
        photos.add("C:\\Users\\Dell\\Desktop\\SlippingDmitry\\yZ03NCVppSA.jpg");
        photos.add("C:\\Users\\Dell\\Desktop\\SlippingDmitry\\Y3uNQprLSo8.jpg");
    }

    public String getRandPhoto()
    {
        //получаем случайное значение в интервале от 0 до самого большого индекса
        int randValue = (int)(Math.random() * photos.size());
        //Из коллекции получаем цитату со случайным индексом и возвращаем ее
        return photos.get(randValue);
    }
}
