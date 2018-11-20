/*
 * Copyright 2013 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.benchmark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * PrintToConsoleCallback implements BenchmarkCallback and simply prints everything to the console.
 *
 */
public class PrintToConsoleCallback implements BenchmarkCallback {

    private static final Logger logger = LoggerFactory.getLogger(PrintToConsoleCallback.class);

    private static final NumberFormat PERCENT_FORMAT = new DecimalFormat("##0.0");

    @Override
    public void begin(Benchmark benchmark, int benchmarkIndex, int benchmarkCount) {
        logger.info("Benchmark " + benchmarkIndex + " / " + benchmarkCount + ": " + benchmark.getTitle());
    }

    @Override
    public void warmup(Benchmark benchmark, boolean finished) {
        if (finished) {
            logger.info("Go! ");
        } else {
            logger.info("Warmup... ");
        }
    }

    @Override
    public void progress(Benchmark benchmark, double percent) {
        logger.info(PERCENT_FORMAT.format(percent) + "% ");
    }

    @Override
    public void success(BenchmarkResult result) {
        logger.info("\n");
        logger.info("\n");
        logger.info(Benchmarks.printResult(result).toString());
        logger.info("\n");
    }

    @Override
    public void aborted(BenchmarkResult result) {
        logger.info("\n");
        logger.info("Benchmark aborted: " + result.getTitle());
        logger.info("Number of errors: " + result.getNumErrors());
    }

    @Override
    public void error(BenchmarkError.Type type, Exception e, BenchmarkResult result) {
        logger.info("Benchmark error of type: " + type);
        logger.error(e.getMessage(), e);
    }

    @Override
    public void fatal(Exception e) {
        logger.info("Fatal benchmark error: " + e.getClass().getSimpleName());
        logger.error(e.getMessage(), e);
    }

}
