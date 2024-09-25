package com.wxxtest.rpc.registration.center.client.rpc.framework.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;

public class ByteBufUtil {

  /**
   * 使用反射对成员变量进行赋值，会改变其原始的封装性
   */
  public static <T> T byteToObject(ByteBuf buf, Class<T> returnTypeClazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    T obj = returnTypeClazz.getDeclaredConstructor().newInstance();
    Field[] fields = returnTypeClazz.getDeclaredFields();
    Arrays.sort(fields, Comparator.comparing(Field::getName));
    for (Field field : fields) {
      switch (field.getType().getSimpleName()) {
        case "String" -> {
          if (field.canAccess(obj)) {
            field.set(obj, buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8));
          } else {
            field.setAccessible(true);
            field.set(obj, buf.readCharSequence(buf.readInt(), StandardCharsets.UTF_8));
            field.setAccessible(false);
          }
        }
        case "Integer", "int" -> {
          if (field.canAccess(obj)) {
            field.set(obj, buf.readInt());
          } else {
            field.setAccessible(true);
            field.set(obj, buf.readInt());
            field.setAccessible(false);
          }

        }
        case "Long", "long" -> {
          if (field.canAccess(obj)) {
            field.set(obj, buf.readLong());
          } else {
            field.setAccessible(true);
            field.set(obj, buf.readLong());
            field.setAccessible(false);
          }
        }
        default -> {
          //对象类型
          if (field.canAccess(obj)) {
            field.set(obj, byteToObject(buf, field.getType()));
          } else {
            field.setAccessible(true);
            field.set(obj, byteToObject(buf, field.getType()));
            field.setAccessible(false);
          }
        }
      }
    }
    return obj;
  }

  public static ByteBuf objectToByte(Object request, Long reqId) throws IllegalAccessException {
    return objectToByte(request, reqId, null);
  }

  public static ByteBuf objectToByte(Object request, Long reqId, String methodName) throws IllegalAccessException {
    ByteBuf buf = Unpooled.buffer();
    //报文长度
    buf.writeInt(-1);

    if (reqId != null) {
      buf.writeLong(reqId);
    }

    if (methodName != null && methodName.trim().length() > 0) {
      byte[] methodNameBytes = methodName.trim().getBytes(StandardCharsets.UTF_8);
      buf.writeInt(methodNameBytes.length);
      buf.writeBytes(methodNameBytes);
    }

    objToBuf(buf, request);

    buf.setInt(0, buf.readableBytes());
    return buf;
  }

  private static void objToBuf(ByteBuf buf, Object obj) throws IllegalAccessException {
    if (obj == null) {
      return;
    }
    Field[] fields = obj.getClass().getDeclaredFields();
    Arrays.sort(fields, Comparator.comparing(Field::getName));
    for (Field f : fields) {
      switch (f.getType().getSimpleName()) {
        case "String" -> {
          String str;
          if (f.canAccess(obj)) {
            str = (String) (f.get(obj));
          } else {
            f.setAccessible(true);
            str = (String) (f.get(obj));
            f.setAccessible(false);
          }
          byte[] methodNameBytes = str.getBytes(StandardCharsets.UTF_8);
          buf.writeInt(methodNameBytes.length);
          buf.writeBytes(methodNameBytes);
        }
        case "int", "Integer" -> {
          if (f.canAccess(obj)) {
            buf.writeInt((int) (f.get(obj)));
          } else {
            f.setAccessible(true);
            buf.writeInt((int) (f.get(obj)));
            f.setAccessible(false);
          }
        }
        case "long", "Long" -> {
          if (f.canAccess(obj)) {
            buf.writeLong((long) (f.get(obj)));
          } else {
            f.setAccessible(true);
            buf.writeLong((long) (f.get(obj)));
            f.setAccessible(false);
          }
        }
        default ->//对象类型
        {
          if (f.canAccess(obj)) {
            objToBuf(buf, f.get(obj));
          } else {
            f.setAccessible(true);
            objToBuf(buf, f.get(obj));
            f.setAccessible(false);
          }

        }
      }
    }
  }
}
