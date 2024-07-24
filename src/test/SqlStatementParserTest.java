import org.junit.Assert;
import org.junit.Test;
import parser.ColumnToken;
import parser.ParserResult;
import parser.SqlStatementParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.fail;

public class SqlStatementParserTest {

    @Test
    public void testWithStatement() {
        String sql = "with y as (SELECT * FROM x.tbl_a) select a,b,c,DATETOSTRING(date_attr_name,'mm/dd/yyyy') from y";
        SqlStatementParser parser = new SqlStatementParser();
        ParserResult result = parser.parseTokens(sql);
        if (result.isSuccess()) {
            Set tokens=result.getTokens();
            if (!tokens.contains(ColumnToken.withColumn("","x","tbl_a","date_attr_name"))) {
                fail("Did not parse x.tbl_a.date_attr_name.");
            }
            if (!tokens.contains(ColumnToken.withColumn("","x","tbl_a","a"))) {
                fail("Did not parse x.tbl_a.a.");
            }
            if (!tokens.contains(ColumnToken.withColumn("","x","tbl_a","b"))) {
                fail("Did not parse x.tbl_a.b.");
            }
            if (!tokens.contains(ColumnToken.withColumn("","x","tbl_a","c"))) {
                fail("Did not parse x.tbl_a.c.");
            }
            if (!tokens.contains(ColumnToken.withColumn("","x","tbl_a","*"))) {
                fail("Did not parse x.tbl_a.*.");
            }
            if (!tokens.contains(ColumnToken.withTable("","x","tbl_a"))) {
                fail("Did not parse x.tbl_a.");
            }
            Assert.assertTrue("Parsed properly.",true);
            // You can also add assertions here to validate the parsing result
            // For example, you can check that specific tokens or structures were parsed correctly.
        } else {
            System.out.println(result.getErrMsg());
            // Fail the test if parsing is unsuccessful
            fail("SQL statement parsing failed");
        }
    }


    @Test
    public void testSimpleStatement() {
        String sql = "select a,b,c,DATETOSTRING(date_attr_name,'mm/dd/yyyy') from x.tbl_a";
        SqlStatementParser parser = new SqlStatementParser();
        ParserResult result = parser.parseTokens(sql);
        if (result.isSuccess()) {
            Set tokens=result.getTokens();
            if (!tokens.contains(ColumnToken.withColumn("","x","tbl_a","date_attr_name"))) {
                fail("Did not parse x.tbl_a.date_attr_name.");
            }
            if (!tokens.contains(ColumnToken.withColumn("","x","tbl_a","a"))) {
                fail("Did not parse x.tbl_a.a.");
            }
            if (!tokens.contains(ColumnToken.withColumn("","x","tbl_a","b"))) {
                fail("Did not parse x.tbl_a.b.");
            }
            if (!tokens.contains(ColumnToken.withColumn("","x","tbl_a","c"))) {
                fail("Did not parse x.tbl_a.c.");
            }
            if (!tokens.contains(ColumnToken.withTable("","x","tbl_a"))) {
                fail("Did not parse x.tbl_a.");
            }
            Assert.assertTrue("Parsed properly.",true);
            // You can also add assertions here to validate the parsing result
            // For example, you can check that specific tokens or structures were parsed correctly.
        } else {
            System.out.println(result.getErrMsg());
            // Fail the test if parsing is unsuccessful
            fail("SQL statement parsing failed");
        }
    }

    @Test
    public void testFailedStatement() {
        String sql = "select cs.DeviceId,cs.MessageDateTime,concat_ws('\\',string(int(cs.BloodPressureSystolic)),string(int(cs.BloodPressureDiastolic))) as BloodPressure, cs.BodyTemperature, cs.HeartRateVariability from iomt_demo.charts_silver cs where cs.DeviceId = '1qsi9p8t5l2'  and cs.HeartRate is not null  and cs.MessageDateHour = date_format(current_timestamp(), 'yyyy-MM-dd HH:00:00') order by cs.MessageDateTime desc LIMIT 1000";

        SqlStatementParser parser = new SqlStatementParser();
        ParserResult result = parser.parseTokens(sql);
        if (result.isSuccess()) {
            Set<ColumnToken> tokens=result.getTokens();
            for (ColumnToken token : tokens) {
                System.out.println(token);
            }

            Assert.assertTrue("Parsed properly.",true);
            // You can also add assertions here to validate the parsing result
            // For example, you can check that specific tokens or structures were parsed correctly.
        } else {
            System.out.println(result.getErrMsg());
            // Fail the test if parsing is unsuccessful
            fail("SQL statement parsing failed");
        }
    }

    @Test
    public void testFailedStatement2() {
        String sql = "select cs.DeviceId,cs.MessageDateTime,concat_ws('\\\\',string(int(cs.BloodPressureSystolic)),string(int(cs.BloodPressureDiastolic))) as BloodPressure, cs.BodyTemperature, cs.HeartRateVariability from iomt_demo.charts_silver cs where cs.DeviceId = '1qsi9p8t5l2'  and cs.HeartRate is not null  and cs.MessageDateHour = date_format(current_timestamp(), 'yyyy-MM-dd HH:00:00') order by cs.MessageDateTime desc LIMIT 1000";

        SqlStatementParser parser = new SqlStatementParser();
        ParserResult result = parser.parseTokens(sql);
        if (result.isSuccess()) {
            Set<ColumnToken> tokens=result.getTokens();
            for (ColumnToken token : tokens) {
                System.out.println(token);
            }

            Assert.assertTrue("Parsed properly.",true);
            // You can also add assertions here to validate the parsing result
            // For example, you can check that specific tokens or structures were parsed correctly.
        } else {
            System.out.println(result.getErrMsg());
            // Fail the test if parsing is unsuccessful
            fail("SQL statement parsing failed");
        }
    }


    @Test
    public void testSimpleStatements() {
        List<String> sqlStatements = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            String sql = "select a" + i + ",b" + i + ",c" + i + ",DATETOSTRING(date_attr_name,'mm/dd/yyyy') from x.tbl_a";
            sqlStatements.add(sql);
        }
        SqlStatementParser parser = new SqlStatementParser();
        List<ParserResult> results = parser.parseMultipleTokens(sqlStatements);
        for (int i = 0; i < results.size(); i++) {
            ParserResult result = results.get(i);
            if (result.isSuccess()) {
                Set tokens = result.getTokens();
                Assert.assertTrue("Found 5 tokens", tokens.size()==5);
            }
        }
    }
}