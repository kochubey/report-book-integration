package logbook.intergation.todo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Serializer {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        try (Stream<Path> walk = Files.walk(Paths.get("C:/Users/Serializer/1"))) {
            List<String> result = walk.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());
            for(String s: result){
                System.out.println(s);
                byte[] _new = Files.readAllBytes(Paths.get(s));
                outputStream.write(_new);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = outputStream.toByteArray();

        Files.write(Paths.get("C:/Users/Serializer/files", "body.zip"), bytes);
    }
}
