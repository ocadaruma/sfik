package com.mayreh.sfik

sealed abstract class ThreadName

case class SfikThread(threadName: ThreadName)
