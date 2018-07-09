# ratecalculator

[![Build Status](https://travis-ci.org/ljd1987/ratecalculator.svg?branch=master)](https://travis-ci.org/ljd1987/ratecalculator)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.ljd.hackajob%3Aratecalculator&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.ljd.hackajob%3Aratecalculator)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.ljd.hackajob%3Aratecalculator&metric=coverage)](https://sonarcloud.io/component_measures?id=com.ljd.hackajob%3Aratecalculator&metric=coverage)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=com.ljd.hackajob%3Aratecalculator&metric=bugs)](https://sonarcloud.io/project/issues?id=com.ljd.hackajob%3Aratecalculator&resolved=false&types=BUG)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.ljd.hackajob%3Aratecalculator&metric=code_smells)](https://sonarcloud.io/project/issues?id=com.ljd.hackajob%3Aratecalculator&resolved=false&types=CODE_SMELL)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=com.ljd.hackajob%3Aratecalculator&metric=vulnerabilities)](https://sonarcloud.io/project/issues?id=com.ljd.hackajob%3Aratecalculator&resolved=false&types=VULNERABILITY)

---

simple loan calculator based on multiple lenders

## Usage

```bash
java -jar [path-to-jar] [path-to-csv-data] [amount]

where:

[path-to-jar] is the path to the executable jar file produced by building this project
[path-to-csv-data] is the path to the market data file
[amount] is the loan request amout, which must be between 1000 and 15000 at 100 increments (as per the spec)
```

## Step by step

1. Clone repo

```bash
$ git clone https://github.com/ljd1987/ratecalculator.git
Cloning into 'ratecalculator'...
remote: Counting objects: 153, done.
remote: Compressing objects: 100% (80/80), done.
remote: Total 153 (delta 41), reused 136 (delta 28), pack-reused 0
Receiving objects: 100% (153/153), 25.27 KiB | 8.42 MiB/s, done.
Resolving deltas: 100% (41/41), done.
```

2. Build

```bash
$ mvn install
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building ratecalculator 1.0.0
[INFO] ------------------------------------------------------------------------
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 5.806 s
[INFO] Finished at: 2018-07-09T20:25:35+01:00
[INFO] Final Memory: 25M/342M
[INFO] ------------------------------------------------------------------------
```

3. Run

```bash
$ java -jar target/ratecalculator-1.0.0.jar src/test/resources/lenderdata.csv 1000
Requested amount: £1000
Rate: 7.0%
Monthly repayment: £30.88
Total repayment: £1111.68
```
