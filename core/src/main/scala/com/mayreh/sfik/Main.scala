package com.mayreh.sfik

import java.io.File

import com.mayreh.sfik.config.SfikConfig

object Main {
  def main(args: Array[String]): Unit = {
    SfikConfig.load(new File(args.head))
  }
}
