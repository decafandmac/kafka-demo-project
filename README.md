# kafka-demo-project

### kafka topic 생성

- kafka-topics.sh `--create` --bootstrap-server localhost:9092 --topic hello.kafka(토픽명)
- kafka-topics.sh `--create` --bootstrap-server localhost:9092 `--partitions 10 --replication-factor 1` --topic
  hello.kafka2 `--config retention.ms=172800000`
- 브로커의 옵션 중 autocreate 옵션이 `true`인 경우, producer/consumer 가 `기존에 없는 토픽`에 요청을 하면 자동으로 생성이 됨

### topic 정보 조회

- `kafka-topics.sh` --bootstrap-server localhost:9092 --topic hello.kafka(토픽명) `--describe`

### topic 정보 수정

- `kafka-topics.sh` --bootstrap-server localhost:9092 --topic hello.kafka(토픽명) `--alter` `--partitions 10`
- `kafka-configs.sh` --bootstrap-server localhost:9092 --topic hello.kafka(
  토픽명) `--alter` `--add-config min.insync.replicas=2`

### server.properties 정보 조회

- kafka-configs.sh --bootstrap-server localhost:9092 --broker 0 --all --describe

### console을 통해 produce

- kafka-console-producer.sh --bootstrap-server localhost:9092 --topic hello.kafka --property "parse.key=true"
  --property "key.separator=:"
    - parse.key 관련 설정을 세팅하지 않으면 key 는 null 이 들어감

### console을 통해 consume

- kafka-console.consumer.sh --bootstrap-server localhost:9092 --topic hello.kafka
    - option
        - --from-beginning
        - --property print.key=true
        - --property key.separator="-"
        - --max-messages
        - --partition
        - --group
            - consumer group 을 통해 commit 한 데이터는 __consumer_offsets 토픽에 저장

### kafka group

- kafka-consumer-groups.sh --bootstrap-server localhost:9092 --topic hello.kafka
    - option
        - --group
        - --reset-offsets
            - --to-earliest
            - --to-latest
            - --to-current
            - --to-datetime {YYYY-MM-DDTHH:mmSS.sss}
            - --to-offset {long}
            - --shift-by {+/- long}
        - --describe

### Etc

- kafka-producer-perf-test.sh
- kafka-consumer-perf-test.sh
- `kafka-reassign-partitions.sh`
- kafka-delete-record.sh
    - 삭제 워터마크만 기록하는 것
- kafka-dump-log.sh
