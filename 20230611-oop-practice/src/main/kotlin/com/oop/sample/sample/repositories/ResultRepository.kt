package com.oop.sample.sample.repositories

import com.oop.sample.sample.models.Result
import com.oop.sample.sample.supports.ActiveRepository

interface ResultRepository : ActiveRepository<Result, Long>