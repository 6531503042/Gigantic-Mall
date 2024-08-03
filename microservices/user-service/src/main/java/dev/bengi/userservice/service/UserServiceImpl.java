package dev.bengi.userservice.service;

import dev.bengi.interfaceservice.proto.user.UserServiceGrpc;
import dev.bengi.interfaceservice.proto.user.UserServiceOuterClass;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {


}
