package org.kenny.multi;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompletableFutureMallDemo {
    static List<OnlineMall> onlineMalls = Arrays.asList(
            new OnlineMall("JD"),
            new OnlineMall("Dangdang"),
            new OnlineMall("Amazon")
    );

    public static List<String> getPrice(List<OnlineMall> onlineMalls, String productName) {
        return onlineMalls.stream().map(
                onLineMall ->
                        String.format(productName + " in %s price is %.2f", onLineMall.getName(), onLineMall.calcPrice(productName))
        ).collect(Collectors.toList());
    }

    public static List<String> getCompletableFuture(List<OnlineMall> onlineMalls, String productName) {
//        ExecutorService executorService = Executors.newFixedThreadPool(onlineMalls.size());
        return onlineMalls.stream().map(
                onLineMall-> CompletableFuture.supplyAsync(
                        ()-> String.format(productName + " in %s price is %.2f", onLineMall.getName(),
                                onLineMall.calcPrice(productName))))
                .collect(Collectors.toList())
                .stream()
                .map(s->s.join())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
//        List<String> priceList = getPrice(onlineMalls,"mysql");
        List<String> priceList = getCompletableFuture(onlineMalls,"mysql");
        for(String price:priceList){
            System.out.println(price);
        }
        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);
    }
}


@AllArgsConstructor
@NoArgsConstructor
class OnlineMall {
    @Getter
    @Setter
    private String name;

    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
class Student {
    private Integer id;
    private String name;
    private String major;
}
