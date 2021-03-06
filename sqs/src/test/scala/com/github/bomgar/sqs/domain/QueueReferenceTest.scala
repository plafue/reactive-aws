package com.github.bomgar.sqs.domain

import org.specs2.mutable.Specification

class QueueReferenceTest extends Specification {
  "A queuereference" should {
    "parse xml list results" in {
      val listQueueResult =
          <ListQueuesResponse xmlns="http://queue.amazonaws.com/doc/2012-11-05/">
            <ListQueuesResult>
              <QueueUrl>https://sqs.eu-west-1.amazonaws.com/1/test-queue1</QueueUrl>
              <QueueUrl>https://sqs.eu-west-1.amazonaws.com/1/test-queue2</QueueUrl>
            </ListQueuesResult> <ResponseMetadata>
            <RequestId>6898b151-0d39-5373-8e75-fceffa5f3342</RequestId>
          </ResponseMetadata>
          </ListQueuesResponse>
      val queueUrls = QueueReference.fromListQueueResult(listQueueResult).map(_.url)

      queueUrls must have size 2
      queueUrls must contain("https://sqs.eu-west-1.amazonaws.com/1/test-queue1")
      queueUrls must contain("https://sqs.eu-west-1.amazonaws.com/1/test-queue2")
    }
  }
}
