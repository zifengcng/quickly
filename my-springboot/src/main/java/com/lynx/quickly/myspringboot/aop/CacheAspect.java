package com.lynx.quickly.myspringboot.aop;

import com.lynx.quickly.myspringboot.annotations.Cacheable;
import com.lynx.quickly.myspringboot.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 缓存切面
 **/
@Component
@Slf4j
@Aspect
@Order(8)
public class CacheAspect {

    /**
     * 定义切入点
     */
    @Pointcut("@within(com.lynx.quickly.myspringboot.annotations.Cacheable) || @annotation(com.lynx.quickly.myspringboot.annotations.Cacheable) ")
    public void cacheAnnotationPointcut() {
    }

    /**
     * 环绕通知
     */
    @Around("cacheAnnotationPointcut()")
//	@Around("cacheAnnotationPointcut() || (execution(public * com.lynx.quickly.myspringboot.service..*.*(..)))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取类及接口的基本信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 目标对象
        Object target = joinPoint.getTarget();
        // 目标类
        Class<?> targetClass = target.getClass();
        // 目标方法参数
        Object[] args = joinPoint.getArgs();
        // 目标方法名
        String methodName = signature.getName();
        // 返回类型
        Class returnType = signature.getReturnType();

        //获取当前方法对象
        Method method = targetClass.getMethod(methodName, signature.getParameterTypes());
        Cacheable annotation = method.getAnnotation(Cacheable.class);
        boolean isString = returnType == String.class;
        System.out.println(isString);

        // 继续执行原方法
        Object result = joinPoint.proceed();

        String simpleName = target.getClass().getSimpleName();
        String firstLowerName = simpleName.substring(0, 1).toLowerCase()
            + simpleName.substring(1);
        Object bean = SpringContextUtil.getBean(firstLowerName);
//		MethodUtil.invoke(method, bean, args);

        return result;
    }

}