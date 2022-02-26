package io.github.censodev.vrms.vrmsserver.data.seeders;

import io.github.censodev.vrms.vrmsserver.utils.BeanUtil;

import java.util.List;

public class Seeder implements Runnable {
    Runnable[] seeders = new Runnable[]{
            BeanUtil.getBean(AccountSeeder.class),
    };

    @Override
    public void run() {
        List.of(seeders).forEach(Runnable::run);
    }
}
