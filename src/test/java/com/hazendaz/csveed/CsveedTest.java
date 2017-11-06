package com.hazendaz.csveed;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.csveed.bean.BeanWriter;
import org.csveed.bean.BeanWriterImpl;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class CsveedTest {

    public class Bean {
        private Integer first;
        private Long    second;

        public Bean(Integer first, Long second) {
            this.first = first;
            this.second = second;
        }

        public Integer getFirst() {
            return first;
        }

        public void setFirst(Integer first) {
            this.first = first;
        }

        public Long getSecond() {
            return second;
        }

        public void setSecond(Long second) {
            this.second = second;
        }
    }

    @Test
    public void bugReason() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        String value = numberFormat.format(6666);
        assertEquals("6,666", value);
    }

    @Test
    public void bugSolution() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        numberFormat.setGroupingUsed(false);
        String value = numberFormat.format(6666);
        assertEquals("6666", value);
    }

    @Test
    public void testLocale() throws IOException {

        List<Bean> beans = new ArrayList<>();
        beans.add(new Bean(2222, 6666L));

        try (StringWriter writer = new StringWriter()) {

            BeanWriter<Bean> beanWriter = new BeanWriterImpl<>(writer, Bean.class);
            beanWriter.writeBeans(beans);
            writer.close();

            System.out.print(writer.getBuffer().toString());
            assertEquals("\"first\";\"second\"\r\n" + "\"2222\";\"6666\"\r\n", writer.getBuffer().toString());
        }

    }
}
